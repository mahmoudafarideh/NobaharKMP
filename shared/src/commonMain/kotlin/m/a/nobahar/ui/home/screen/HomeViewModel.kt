package m.a.nobahar.ui.home.screen

import kotlinx.collections.immutable.toImmutableList
import m.a.nobahar.domain.model.CenturyPoets
import m.a.nobahar.domain.model.LoadableData
import m.a.nobahar.domain.model.Loaded
import m.a.nobahar.domain.model.NotLoaded
import m.a.nobahar.domain.model.Poet
import m.a.nobahar.domain.repository.CenturyPoetsRepository
import m.a.nobahar.domain.repository.HomeCommunicationRepository
import m.a.nobahar.ui.home.model.CenturyUiModel
import m.a.nobahar.ui.home.model.HomeUiModel
import m.a.nobahar.ui.home.model.toHomeCommunicationUiModel
import m.a.nobahar.ui.shared.BaseViewModel
import m.a.nobahar.ui.shared.model.PoetUiModel

class HomeViewModel(
    private val repository: CenturyPoetsRepository,
    private val homeCommunicationRepository: HomeCommunicationRepository
) : BaseViewModel<LoadableData<HomeUiModel>>(
    NotLoaded
) {

    private var centuryPoets: List<CenturyPoets> = emptyList()

    init {
        getCenturyPoets()
    }

    private fun getCenturyPoets() {
        executeLoadable(
            currentValue = state.value,
            action = {
                getPoets()
            },
            data = {
                updateState { it }
            }
        )
    }

    private suspend fun getPoets(): HomeUiModel {
        val centuries = repository.getCenturies()
        val popularPoets = centuries.firstOrNull()?.poets.orEmpty().map {
            it.toPoetUiModel()
        }.toImmutableList()
        val realCenturies = centuries.filter { it.id > 0 }
        centuryPoets = realCenturies
        val labels = realCenturies.mapIndexed { index, centuryPoets ->
            toCenturyUiModel(centuryPoets, index)
        }.toImmutableList()

        val poets = realCenturies.firstOrNull()?.poets.orEmpty().map {
            it.toPoetUiModel()
        }.toImmutableList()

        return HomeUiModel(
            popularPoets = popularPoets,
            labels = labels,
            poets = poets,
            communication = homeCommunicationRepository.getCommunication()
                ?.toHomeCommunicationUiModel()
        )
    }

    private fun toCenturyUiModel(
        centuryPoets: CenturyPoets,
        index: Int
    ) = CenturyUiModel(
        label = centuryPoets.name,
        isSelected = index == 0
    )

    private fun Poet.toPoetUiModel() = PoetUiModel(
        id = id,
        name = name,
        profile = profile,
        nickname = nickName
    )

    fun centuryClicked(century: String) {
        state.value.data?.let {
            val centuries = it.labels.map { uiModel ->
                uiModel.copy(isSelected = uiModel.label == century)
            }.toImmutableList()
            updateState {
                Loaded(
                    it.copy(
                        labels = centuries,
                        poets = centuryPoets.firstOrNull { it.name == century }?.poets.orEmpty()
                            .map {
                                it.toPoetUiModel()
                            }.toImmutableList()
                    )
                )
            }
        }
    }

    fun retryClicked() {
        getCenturyPoets()
    }
}