# RooomPractical
Learning about Room with MVVM and Flow</br>



# How Room helps us.</br>
In android we are using the SQLite data base. But, we need to write a lot of complex codes to 
effectively work with SQLite. Fortunately, now we have Room library. Room makes our work much easier
by generating all the required complex codes in the background. However, we have to tell room what to
do. We communicate with room library using annotations. Room only recognize things by reading annotations.

# Project setup.
1)Add the kotlin-kapt plugin to the top of the gradle.

```gradle
plugins {
    id 'com.android.application'
    id 'org.jetbrains.kotlin.android'
    id 'kotlin-kapt'
    id 'kotlin-android'
}
```
2) Add the gradle dependencies required to work with Room ORM (object relational mapper)


```gradle
dependencies {
    
    // room
    
    implementation "androidx.room:room-runtime:2.5.0"
    kapt "androidx.room:room-compiler:2.5.0"
    implementation "androidx.room:room-ktx:2.5.0"
    }
```

3)Add dependencies required to use Kotlin Coroutines.
```gradle
dependencies {
    
    // room
    def room_version = "2.2.6"
    implementation "androidx.room:room-runtime:$room_version"
    kapt "androidx.room:room-compiler:$room_version"
    implementation "androidx.room:room-ktx:$room_version"
    
    // coroutines

    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1'

    
    }
```

4)Add the dependencies required to use ViewModel and LiveData

```gradle
dependencies {
    
    // room
    def room_version = "2.2.6"
    implementation "androidx.room:room-runtime:$room_version"
    kapt "androidx.room:room-compiler:$room_version"
    implementation "androidx.room:room-ktx:$room_version"
    
    // coroutines

    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.2'
    
    //View model and live data
    def lifecycle_version = "2.3.1"
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1"

    // LiveData
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"

    implementation "androidx.lifecycle:lifecycle-common-java8:$lifecycle_version"
    
    }
```

5)Enable data-binding
```gradle
 buildFeatures {
        dataBinding = true
    }
```

# Steps 

1) Create a Room Entity class.
 ```kotlin
 @Entity(tableName = "table_name")
data class Users(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    var id: Int,

    @ColumnInfo(name = "user_name")
    var name: String,

    @ColumnInfo(name = "user_email")
    var email: String

)
 
 ```
2) create a DAO interface with all required room functions(methods)
```kotlin
@Dao
interface SubscriberDAO {
 
    @Insert
    suspend fun insertUser(user: User) : Long
 
    @Update
    suspend fun updateUser(user: User) : Int
 
    @Delete
    suspend fun deleteUser(user: User) : Int
 
    @Query("DELETE FROM user_data_table")
    suspend fun deleteAll() : Int
 
    @Query("SELECT * FROM user_data_table")
    fun getAllSubscribers():Flow<List<User>>
}

```

3)RoomDatabase Class
In order to use Room library,  we need an instance of RoomDatabase class. For that, we should create an abstract subclass of RoomDatabase class</br>
```kotlin
@Database(entities = [User::class], version = 1)
abstract class UserDataBase : RoomDatabase() {

    abstract val UserDAO: UserDAO

    companion object {

        private var INSTANCE: UserDataBase? = null

        fun getInstance(ctx: Context): UserDataBase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        ctx.applicationContext,
                        UserDataBase::class.java,
                        "UserDataBase"
                    ).build()
                }
                return instance
            }

        }
    }
}

```
<a href="https://github.com/Suraj820/RooomPractical/tree/Automigration">Automigration in room </a>





# Refrence
<a href="https://appdevnotes.com/android-mvvm-project-example/">appdevnotes</a>
