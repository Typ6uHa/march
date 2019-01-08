package aa.com.marchenko.ui.base

import aa.com.marchenko.TokenStorage
import aa.com.marchenko.addSchedulers
import aa.com.marchenko.data.storage.model.Course
import aa.com.marchenko.data.storage.model.User
import aa.com.marchenko.data.storage.repository.StorageRepository
import aa.com.marchenko.data.storage.repository.StorageRepositoryProvider
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import kotlin.math.roundToInt

abstract class BasePresenter<View : BaseView> : MvpPresenter<View>() {
    protected val disposable: CompositeDisposable = CompositeDisposable()
    private val storageRepository: StorageRepository = StorageRepositoryProvider.instance

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        if (!TokenStorage.getToken()) {
            Completable.fromAction {
                storageRepository.add(Course(id = 0L, description = "Узнай истории России в увлекательном курсе - История от И до Я", name = "История россии", elo = 900.00))
                storageRepository.add(Course(id = 0L, description = "Курс по линейной алгебре от Арсланова М.М.", name = "Линейная алгебра", elo = 1300.00))
                storageRepository.add(Course(id = 0L, description = "Курс по аналитической геометрии от Арсланова М.М.", name = "Аналитическая геометрия", elo = 1599.00))
                storageRepository.add(Course(id = 0L, description = "Математический анализ : Атака клонов", name = "Математический анализ", elo = 1700.00))
                storageRepository.add(Course(id = 0L, description = "Математический анализ : Скрытая угроза", name = "Введение в курс математического анализа", elo = 1399.00))
                storageRepository.add(Course(id = 0L, description = "Математический анализ : Месть ситхов", name = "Продвинутый курс математического анализа", elo = 2100.00))
                storageRepository.add(Course(id = 0L, description = "Построение баз данных в СУБД PostgreSQL", name = "База данных", elo = 2200.00))
                storageRepository.add(Course(id = 0L, description = "Курс Экономика от Э до А", name = "Экономика", elo = 877.00))
                storageRepository.add(Course(id = 0L, description = "Изнайте все то, что от вас скрывали в школе", name = "Русский Язык", elo = 603.00))
                storageRepository.add(Course(id = 0L, description = "Machine learning is a method of data analysis that automates analytical model building", name = "Machine Learning", elo = 2403.00))
                storageRepository.add(Course(id = 0L, description = "Научим вас правилам игры в Catan", name = "Catan", elo = 1103.00))
                storageRepository.add(Course(id = 0L, description = "Научим вас правилам игры в шашки", name = "Шахматы", elo = 1203.00))
                storageRepository.add(Course(id = 0L, description = "ORM в hibernate", name = "ORM в hibernate", elo = 1199.00))
                storageRepository.add(Course(id = 0L, description = "ООП в java", name = "ООП в java", elo = 987.00))
                storageRepository.add(Course(id = 0L, description = "Школьный курс по физике", name = "Школьный курс по физике", elo = 789.00))
                storageRepository.add(Course(id = 0L, description = "Школьный курс по химии", name = "Школьный курс по химии", elo = 365.00))
                storageRepository.add(Course(id = 0L, description = "Курс по языку программирования на языке Python", name = "Курс по языку программирования на языке Python", elo = 1687.00))
                storageRepository.add(Course(id = 0L, description = "Курс по языку программирования на языке GO", name = "Курс по языку программирования на языке GO", elo = 1987.00))
                storageRepository.add(Course(id = 0L, description = "Курс по Алгоритмам и структурам данных", name = "Алгоритмы и структуры данных", elo = 1789.00))
                storageRepository.add(Course(id = 0L, description = "Научим вас правилам игры в шашки", name = "Шашки", elo = 367.00))
                storageRepository.add(Course(id = 0L, description = "Курс по языку программирования на языке Kotlin", name = "Курс по языку программирования на языке Kotlin", elo = 1800.00))
                storageRepository.add(Course(id = 0L, description = "Курс по языку программирования на языке C", name = "Курс по языку программирования на языке C", elo = 1354.00))
                storageRepository.add(Course(id = 0L, description = "Курс по языку программирования на языке C++", name = "Курс по языку программирования на языке C++", elo = 1387.00))
                storageRepository.add(Course(id = 0L, description = "Курс по языку программирования на языке C#", name = "Курс по языку программирования на языке C#", elo = 1546.00))
                storageRepository.addUser(User(id = 0L, name = "Aizat", elo = 1400.00))
            }.addSchedulers().subscribe()
            TokenStorage.putToken()
        } else {
        }
    }

    fun selectCourse(selected: Int, courseId: Long) {
        disposable += Completable.fromAction {
            storageRepository.selectCourse(selected, courseId)
        }.addSchedulers().subscribe()
    }

    fun finishCourse(courseId: Long, eloCourse: Double) {
        disposable += Completable.fromAction {
            val user = storageRepository.getUsers().map { it.first() }.blockingGet()
            storageRepository.setEloToCourse(getNewElo(eloCourse,user.elo,false),courseId)
            storageRepository.setEloToUser(getNewElo(user.elo,eloCourse,true),user.id)
            storageRepository.selectCourse(0,courseId)
        }.addSchedulers().subscribe()
    }

    fun skipCourse(courseId: Long, eloCourse: Double) {
        disposable += Completable.fromAction {
            val user = storageRepository.getUsers().map { it.first() }.blockingGet()
            storageRepository.setEloToCourse(getNewElo(eloCourse,user.elo,true),courseId)
            storageRepository.setEloToUser(getNewElo(user.elo,eloCourse,false),user.id)
            storageRepository.selectCourse(0,courseId)
        }.addSchedulers().subscribe()
    }

    private fun getNewElo(player1: Double, player2: Double, win: Boolean): Double {
        return if (win) {
            player1 + (40 * (1 - getMathWaiting(player1, player2)))
        } else {
            player1 + (40 * (0 - getMathWaiting(player1, player2)))
        }
    }

    private fun getMathWaiting(player1: Double, player2: Double): Double {
        return (1 / (1 + Math.pow(10.0, ((player2 - player1) / 400))))
    }
}

