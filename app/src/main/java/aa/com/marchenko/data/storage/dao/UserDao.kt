package aa.com.marchenko.data.storage.dao

import aa.com.marchenko.data.storage.model.Course
import aa.com.marchenko.data.storage.model.User
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import io.reactivex.Single

@Dao
interface UserDao {

    @Insert
    fun add(user: User): Long

    @Query("Select * from user")
    fun getUser(): Single<List<User>>

    @Query("Update user set elo= :elo where id=:userId")
    fun setNewEloToUser(elo: Double, userId: Long)
}