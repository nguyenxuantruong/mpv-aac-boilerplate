#  Android MVP Architecture With AAC (Android Architecture Components)

This is code skeleton to build Android Application base on MVP pattern and ViewModel, LiveDat (in Android Architecture Components)

####The boilerplate code include: 

1. [Dagger 2](https://github.com/google/dagger): The fast dependency injector for Android and Java
2. [Realm](https://realm.io): a mobile database
3. [Retrofit](http://square.github.io/retrofit/): is a REST Client for Android and Java
4. [RxJava 2](https://github.com/ReactiveX/RxJava): Reactive Extensions for the JVM – a library for composing asynchronous and event-based programs using observable sequences for the Java VM.
5. [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel): to store and manage UI-related data in a lifecycle conscious way.
6. [LiveData](https://developer.android.com/topic/libraries/architecture/livedata):  is a data holder class that can be observed within a given lifecycle.


####The main features:
1. Save data into local storage with Realm (can use Room instead of).
2. Chaining multiple sources with RxJava.
3. Error handling.
4. Save data alive on configuration changes with .
5. Use LiveData to triggering the UI changes if there is any change in data properties.
