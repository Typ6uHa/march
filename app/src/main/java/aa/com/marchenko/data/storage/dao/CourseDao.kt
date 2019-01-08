package aa.com.marchenko.data.storage.dao

import aa.com.marchenko.data.storage.model.Course
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable

@Dao
interface CourseDao {
    @Insert
    fun add(course: Course): Long

    @Query("Delete from course where id = :courseId")
    fun delete(courseId: Long)

    @Query("Select * from course where elo between :userElo-200 and :userElo+200")
    fun getRecomendateCourses(userElo: Double): Flowable<List<Course>>

    @Query("Select * from course")
    fun getAllCourse(): Flowable<List<Course>>

    @Query("Select * from course where selected = 1")
    fun getSelectedCourses(): Flowable<List<Course>>

    @Query("Update course SET selected= :selected where id = :courseId")
    fun selectCourse(selected: Int, courseId: Long)

    @Query("Update course set elo= :elo where id=:courseId")
    fun setNewEloToUser(elo: Double, courseId: Long)
}