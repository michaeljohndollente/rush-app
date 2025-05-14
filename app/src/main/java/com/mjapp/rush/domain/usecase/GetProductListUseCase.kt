package com.mjapp.rush.domain.usecase

import com.mjapp.rush.core.common.DataState
import com.mjapp.rush.data.model.product.ProductDataResponse
import com.mjapp.rush.data.repository.StoreRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductListUseCase @Inject constructor(private val repository: StoreRepositoryImpl) {
    operator fun invoke(page: Int, branchUuid: String, brandUuid: String): Flow<DataState<ProductDataResponse>> =
        repository.getProductList(page, branchUuid, brandUuid)
}
