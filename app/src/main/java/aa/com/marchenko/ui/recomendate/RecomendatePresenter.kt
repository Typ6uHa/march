package aa.com.marchenko.ui.recomendate

import aa.com.marchenko.addSchedulers
import aa.com.marchenko.data.storage.model.User
import aa.com.marchenko.data.storage.repository.StorageRepository
import aa.com.marchenko.data.storage.repository.StorageRepositoryProvider
import com.arellomobile.mvp.InjectViewState
import io.reactivex.rxkotlin.plusAssign
import aa.com.marchenko.ui.base.BasePresenter
import io.reactivex.Completable

@InjectViewState
class RecomendatePresenter : BasePresenter<RecomendateView>() {

    private val repository: StorageRepository = StorageRepositoryProvider.instance
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        disposable += Completable.fromAction {
            repository.getUsers().addSchedulers().subscribe { it ->
                if (it.isEmpty()) {
                    val user = User(0L, "Aizat", 1400.0)
                    repository.addUser(user)
                    repository.getRecomendateCourses(userElo = user.elo).addSchedulers().subscribe { it ->
                        viewState.submitList(it)
                    }
                } else {
                    repository.getRecomendateCourses(userElo = it.first().elo).addSchedulers().subscribe { it ->
                        viewState.submitList(it)
                    }
                }
            }
        }.addSchedulers().subscribe()
    }
}