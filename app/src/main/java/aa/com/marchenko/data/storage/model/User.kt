package aa.com.marchenko.data.storage.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "user")
data class User(
        @PrimaryKey(autoGenerate = true)
        val id: Long,
        val name: String,
        val elo: Double
)