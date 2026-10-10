package com.mohamed.smartoffice.ui.spaces

import com.mohamed.smartoffice.data.fake.FakeSpaces
import com.mohamed.smartoffice.data.repository.FakeSpaceRepository
import com.mohamed.smartoffice.domain.model.Equipment
import com.mohamed.smartoffice.domain.model.SpaceType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SpaceListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: SpaceListViewModel

    @Before
    fun setUp() {
        viewModel = SpaceListViewModel(FakeSpaceRepository())
    }

    /** `WhileSubscribed` ne calcule l'état que si quelqu'un l'écoute : on simule l'écran. */
    private fun TestScope.startCollecting() {
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.uiState.collect()
        }
    }

    @Test
    fun `sans ecran qui ecoute - etat de chargement`() {
        assertTrue(viewModel.uiState.value.isLoading)
    }

    @Test
    fun `au demarrage - tous les espaces`() = runTest {
        startCollecting()
        val state = viewModel.uiState.value
        assertFalse(state.isLoading)
        assertEquals(FakeSpaces.all.size, state.spaces.size)
    }

    @Test
    fun `filtre par type - uniquement les salles`() = runTest {
        startCollecting()
        viewModel.onTypeSelected(SpaceType.MEETING_ROOM)
        assertEquals(3, viewModel.uiState.value.spaces.size)
    }

    @Test
    fun `type et equipement combines`() = runTest {
        startCollecting()
        viewModel.onTypeSelected(SpaceType.MEETING_ROOM)
        viewModel.onEquipmentToggled(Equipment.VIDEO_CONFERENCE)
        assertEquals(
            listOf("Salle Atlas", "Salle Sahara"),
            viewModel.uiState.value.spaces.map { it.name },
        )
    }

    @Test
    fun `cliquer deux fois sur un equipement le retire`() = runTest {
        startCollecting()
        viewModel.onEquipmentToggled(Equipment.WEBCAM)
        viewModel.onEquipmentToggled(Equipment.WEBCAM)
        assertTrue(viewModel.uiState.value.criteria.equipments.isEmpty())
        assertEquals(FakeSpaces.all.size, viewModel.uiState.value.spaces.size)
    }
}