package com.application.contactapplication.ui

import android.Manifest
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.contactapplication.R
import com.application.contactapplication.adapters.ContactAdapter
import com.application.contactapplication.database.ContactDTO
import com.application.contactapplication.databinding.FragmentHomeBinding
import com.application.contactapplication.interfaces.OnCardClicked
import com.application.contactapplication.viewmodels.ContactViewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), OnCardClicked {

    val CONTACT_CODE = 100
    lateinit var bindingHomeFragment: FragmentHomeBinding
    val contactDTOList: ArrayList<ContactDTO> = ArrayList()
    lateinit var adapter: ContactAdapter
    private val viewModel: ContactViewModels by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingHomeFragment =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return bindingHomeFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestPermission()
        loadContacts()
        setRecyclerView()
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.READ_CONTACTS),
            CONTACT_CODE
        )
    }

    private fun loadContacts() {
        getContactList()
        viewModel.insertDataToDb(contactDTOList)
        viewModel.getAppContactFromDb().observe(viewLifecycleOwner, Observer {
            contactDTOList.clear()
            contactDTOList.addAll(it)
            adapter.notifyDataSetChanged()
        })

        viewModel.getAllPagingData(contactDTOList).observe(viewLifecycleOwner, Observer {
            CoroutineScope(Dispatchers.IO).launch {
                adapter.submitData(it)
            }
            adapter.notifyDataSetChanged()
        })
    }

    private fun getContactList() {
        val cursr = activity?.contentResolver
        val cursor: Cursor? = cursr?.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            MULTIPLEDATA,
            null,
            null,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        )
        if (cursor != null) {
            val mobileNoSet = HashSet<String>()
            try {
                val nameIndex: Int = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
                val numberIndex: Int =
                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                var name: String
                var number: String
                while (cursor.moveToNext()) {
                    name = cursor.getString(nameIndex)
                    number = cursor.getString(numberIndex)
                    number = number.replace(" ", "")
                    if (!mobileNoSet.contains(number)) {
                        contactDTOList.add(ContactDTO(name, number))
                        mobileNoSet.add(number)
                        Log.d(
                            "hvy", "onCreaterrView  Phone Number: name = " + name
                                    + " No = " + number
                        )
                    }
                }
            } finally {
                cursor.close()
            }
        }

    }

    private val MULTIPLEDATA = arrayOf(
        ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
        ContactsContract.Contacts.DISPLAY_NAME,
        ContactsContract.CommonDataKinds.Phone.NUMBER
    )

    private fun setRecyclerView() {
        adapter = ContactAdapter(contactDTOList, this)
        bindingHomeFragment.recyclerView.layoutManager = LinearLayoutManager(context)
        bindingHomeFragment.recyclerView.adapter = adapter
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
        } else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    permissions[0]
                )
            ) {
                Toast.makeText(context, "Permissions denied", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    context,
                    "Permissions denied by selecting do not show again",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onCardClicked(contactDTO: ContactDTO) {
        val contactCount = contactDTO.contactCount

        if (contactCount != null) {
            contactDTO.contactCount = contactCount + 1
        }
        viewModel.updateContact(contactDTO)
    }
}
/*
 loadContacts()
        setRecyclerView()
    }

    private fun setRecyclerView() {
        adapter = ContactAdapter(contactDTOList, this)
        bindingHomeFragment.recyclerView.layoutManager = LinearLayoutManager(context)
        bindingHomeFragment.recyclerView.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount =
                    (bindingHomeFragment.recyclerView.layoutManager as LinearLayoutManager).childCount
                val pastVisibleItem =
                    (bindingHomeFragment.recyclerView.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
                val total = adapter.itemCount

                if (!isLoading) {

                    if ((visibleItemCount + pastVisibleItem) >= total) {
                        page++
                        loadContacts()
                    }

                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    @SuppressLint("Range")
    fun loadContacts() {
        isLoading = true
        bindingHomeFragment.progressBar.visibility = View.VISIBLE
        val start = ((page) * limit) + 1
        val end = (page + 1) * limit
        val contacts = activity?.contentResolver?.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        if (contacts != null) {
            while (contacts.moveToNext()) {
                val name =
                    contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val number =
                    contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                val obj = ContactDTO()
                obj.name = name
                obj.phoneNumber = number
                for (i in start..end) {
                    contactDTOList.add(obj)
                }
            }
            Handler().postDelayed({
                if (::adapter.isInitialized) {
                    adapter.notifyDataSetChanged()
                } else {
                    adapter = ContactAdapter(contactDTOList, this)
                }
                isLoading = false
                bindingHomeFragment.progressBar.visibility = View.GONE
            }, 5000)
        }
    }

 */