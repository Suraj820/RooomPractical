# RooomPractical
Learning about Room with MVVM and Flow</br>



# How Room helps us.</br>
In android we are using the SQLite data base. But, we need to write a lot of complex codes to 
effectively work with SQLite. Fortunately, now we have Room library. Room makes our work much easier
by generating all the required complex codes in the background. However, we have to tell room what to
do. We communicate with room library using annotations. Room only recognize things by reading annotations.

#Project setup.
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
    def room_version = "2.2.6"
    implementation "androidx.room:room-runtime:$room_version"
    kapt "androidx.room:room-compiler:$room_version"
    implementation "androidx.room:room-ktx:$room_version"
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

    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.2'
    
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
    // ViewModel
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
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









# Refrence
<a href="https://appdevnotes.com/android-mvvm-project-example/">appdevnotes</a>
