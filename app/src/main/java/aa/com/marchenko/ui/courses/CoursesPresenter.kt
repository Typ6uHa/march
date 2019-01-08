package aa.com.marchenko.ui.courses

import aa.com.marchenko.addSchedulers
import aa.com.marchenko.data.storage.repository.StorageRepository
import com.arellomobile.mvp.InjectViewState
import aa.com.marchenko.data.storage.repository.StorageRepositoryProvider
import aa.com.marchenko.ui.base.BasePresenter

@InjectViewState
class CoursesPresenter : BasePresenter<CoursesView>() {

    private val repository: StorageRepository = StorageRepositoryProvider.instance

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        repository.getAllCourse().addSchedulers().subscribe { it ->
            viewState.submitList(it)
        }
    }
}