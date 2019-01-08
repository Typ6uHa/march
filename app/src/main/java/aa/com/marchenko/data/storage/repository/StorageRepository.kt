package aa.com.marchenko.data.storage.repository

import aa.com.marchenko.data.storage.model.Course
import aa.com.marchenko.data.storage.model.User
import io.reactivex.Flowable
import io.reactivex.Single

interface StorageRepository {
    fun add(course: Course)
    fun delete(courseId: Long)
    fun getRecomendateCourses(userElo: Double): Flowable<List<Course>>
    fun getAllCourse(): Flowable<List<Course>>
    fun getSelectedCourse(): Flowable<List<Course>>
    fun addUser(user: User)
    fun getUsers(): Single<List<User>>
    fun selectCourse(selected: Int, courseId: Long)
    fun setEloToUser(elo: Double, userId: Long)
    fun setEloToCourse(elo: Double, courseId: Long)
}