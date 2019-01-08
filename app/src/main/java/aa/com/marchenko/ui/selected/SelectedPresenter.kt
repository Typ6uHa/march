package aa.com.marchenko.ui.selected

import aa.com.marchenko.addSchedulers
import aa.com.marchenko.data.storage.repository.StorageRepository
import aa.com.marchenko.data.storage.repository.StorageRepositoryProvider
import com.arellomobile.mvp.InjectViewState
import aa.com.marchenko.ui.base.BasePresenter
import io.reactivex.rxkotlin.plusAssign

@InjectViewState
class SelectedPresenter : BasePresenter<SelectedView>() {

    private val repository: StorageRepository = StorageRepositoryProvider.instance

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        disposable += repository.getSelectedCourse()
                .addSchedulers().subscribe { it ->
                    viewState.submitList(it)
                }
    }
}