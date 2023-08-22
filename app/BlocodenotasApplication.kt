import android.app.Application

class BlocodenotasApplication:Application() {
    lateinit var dataBase:AppDataBase

    override fun onCreate(){
        super.onCreate()

        dataBase = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java, "blocodenota-database"
        ).build()
    }
}