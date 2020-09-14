package  com.demo.princichristipracticaltask.Repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = arrayOf(ResultModel::class), version = 1)
abstract class UserInfoRoomDataBase : RoomDatabase() {

    abstract fun loginDao(): UserInfoDao

    //Database create
    companion object {

        @Volatile
        private var INSTANCE: UserInfoRoomDataBase? = null

        fun getDataseClient(context: Context): UserInfoRoomDataBase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, UserInfoRoomDataBase::class.java, "USER_DATABASE")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }


}


