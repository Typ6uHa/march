package aa.com.marchenko.data.storage.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "course")
data class Course(
        @PrimaryKey(autoGenerate = true)
        val id: Long,
        val name: String,
        val description: String,
        val elo: Double,
        val selected: Int = 0
)