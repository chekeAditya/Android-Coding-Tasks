package com.application.addresssearch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import com.application.addresssearch.remote.ApiClient
import com.application.addresssearch.remote.Status
import com.application.addresssearch.remote.responses.ResponseDTO
import com.application.addresssearch.viewmodels.AppViewModel
import io.reactivex.Observable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.net.SocketException

class APITest {

//    @Mock
//    lateinit var apiClient: ApiClient
//
//    @Mock
//    lateinit var appViewModel: AppViewModel
//
//    @Mock
//    lateinit var responseDTO: ResponseDTO
//
//    @Mock
//    lateinit var socketException: SocketException
//
//    lateinit var success: ResponseDTO
//    lateinit var failure: ResponseDTO
//
//    @Before
//    fun setUp() {
//        MockitoAnnotations.initMocks(this)
//        appViewModel = AppViewModel()
//        CoroutineScope(Dispatchers.IO).launch {
//            data()
//        }
//    }
//
//    @Test
//    fun getting_matching_addresses_success() {
//        appViewModel.makeApiCall("mumbai")
//        assertEquals(success.dataModel, Status.SUCCESS)
//
//    }
//
//    @Test
//    fun getting_matching_addresses_failed() {
//        appViewModel.makeApiCall("mumbai")
//        assertEquals(failure.dataModel, Status.ERROR)
//    }
//
//
//    fun data() {
//        `when`<Observable<ResponseDTO>>(
//            apiClient.getResponse(
//                "airtel",
//                ""
//            )
//        ).thenReturn(responseDTO)
//
//        `when`<MutableLiveData<ResponseDTO>>(
//            appViewModel.makeApiCall("mumbai")
//        ).thenReturn(responseDTO)
//    }

}