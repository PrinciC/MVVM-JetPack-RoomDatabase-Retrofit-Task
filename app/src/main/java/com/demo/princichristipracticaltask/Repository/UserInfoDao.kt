import com.mvvm.kot.Kotlin_Retrofit_Room.Repository.ResultModel
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface UserInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user:User)

    @Query("SELECT * from user_info ORDER BY user_name ASC")
    fun getAllUsers():LiveData<List<User>>

    @Query("DELETE FROM user_info")
    fun deleteAll()
}