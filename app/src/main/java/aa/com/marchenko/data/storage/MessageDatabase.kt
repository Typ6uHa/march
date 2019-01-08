package aa.com.marchenko.data.storage

import aa.com.marchenko.data.storage.dao.CourseDao
import aa.com.marchenko.data.storage.dao.UserDao
import aa.com.marchenko.data.storage.model.Course
import aa.com.marchenko.data.storage.model.User
import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase


@Database(entities = [Course::class, User::class], version = 3)
abstract class MessageDatabase : RoomDatabase() {
    abstract fun getMessageDAO(): CourseDao
    abstract fun getUserDAO(): UserDao
}