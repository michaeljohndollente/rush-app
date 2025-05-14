package com.mjapp.rush.domain.usecase

import com.mjapp.rush.core.common.DataState
import com.mjapp.rush.data.repository.StoreRepositoryImpl
import com.mjapp.rush.domain.model.Category
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(private val repository: StoreRepositoryImpl) {
    operator fun invoke(branchUuid: String): Flow<DataState<List<Category>>> =
        repository.getCategories(branchUuid)
}
