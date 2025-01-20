package com.example.photonapplication

import com.example.photonapplication.data.SchoolScreenState
import com.example.photonapplication.data.SchoolsDataResult
import com.example.photonapplication.data.vo.SchoolData
import com.example.photonapplication.domain.SchoolsRepository
import com.example.photonapplication.ui.ui.SchoolsViewModel
import io.mockk.coEvery
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Before
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SchoolsViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()


    private lateinit var viewModel: SchoolsViewModel
    private lateinit var schoolsRepository: SchoolsRepository

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        schoolsRepository = mockk()
        viewModel = SchoolsViewModel(schoolsRepository)
    }

    @Test
    fun `getSchoolsList when successful should update state to Success`() = runTest {
        // Given
        val schools = listOf(
            SchoolData("School 1"),
            SchoolData("School 2")
        )
        coEvery { schoolsRepository.loadSchoolsData() } returns SchoolsDataResult.Success(schools)

        // When
        viewModel.getSchoolsList()

       // Then
        assertEquals(viewModel.state.value, SchoolScreenState.Success(schools))
    }

    @Test
    fun `getSchoolsList when error should update state to Error`() = runTest {
        // Given
        val errorMessage = "Network error"
        coEvery { schoolsRepository.loadSchoolsData() } returns SchoolsDataResult.Error(errorMessage)

        // When
        viewModel.getSchoolsList()

        // Then
        assertEquals(viewModel.state.value, SchoolScreenState.Error(errorMessage))
    }

    @Test
    fun `initial state should be Loading`() = runTest {
        // Then
        assertEquals(SchoolScreenState.Loading, viewModel.state.value)
    }


}