package aa.com.marchenko.data.storage.repository

object StorageRepositoryProvider {
    val instance: StorageRepository by lazy {
        StorageRepositoryImpl()
    }
}