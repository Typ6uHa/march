package aa.com.marchenko.data.storage.repository

import aa.com.marchenko.App
import aa.com.marchenko.data.storage.MessageDatabase
import aa.com.marchenko.data.storage.model.Course
import aa.com.marchenko.data.storage.model.User
import android.arch.persistence.room.Room
import io.reactivex.Flowable
import io.reactivex.Single

class StorageRepositoryImpl : StorageRepository {

    private val database: MessageDatabase = Room.databaseBuilder(App.context, MessageDatabase::class.java, "message_database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

    override fun add(course: Course) {
        database.getMessageDAO().add(course)
    }

    override fun delete(courseId: Long) {
        database.getMessageDAO().delete(courseId)
    }

    override fun getRecomendateCourses(userElo: Double): Flowable<List<Course>> = database.getMessageDAO().getRecomendateCourses(userElo)

    override fun getAllCourse(): Flowable<List<Course>> = database.getMessageDAO().getAllCourse()

    override fun getSelectedCourse(): Flowable<List<Course>> = database.getMessageDAO().getSelectedCourses()

    override fun addUser(user: User) {
        database.getUserDAO().add(user)
    }

    override fun getUsers(): Single<List<User>> = database.getUserDAO().getUser()

    override fun selectCourse(selected: Int, courseId: Long) {
        database.getMessageDAO().selectCourse(selected, courseId)
    }

    override fun setEloToCourse(elo: Double, courseId: Long) {
        database.getMessageDAO().setNewEloToUser(elo, courseId)
    }

    override fun setEloToUser(elo: Double, userId: Long) {
        database.getUserDAO().setNewEloToUser(elo, userId)
    }
}