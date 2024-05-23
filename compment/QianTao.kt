package com.nyvc.ledbanner.compment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * 扩展 LazyColum 嵌套 LazyVerticalGrid
 * @param data 数据
 * @param columnCount 列
 */
fun <T> LazyListScope.gridVerticalItems(
    data: List<T>,
    key: ((index: Int) -> Any)? = null,
    columnCount: Int,
    modifier: Modifier = Modifier.fillMaxWidth(),
    horizontalSpace: Int=10,
    verticalSpace:Int=10,
    itemContent: @Composable BoxScope.(Int,T) -> Unit,
) {
    val size = data.count()
    val rows = if (size == 0) 0 else 1 + (size - 1) / columnCount
    items(rows, key = key) { rowIndex ->
        Column(modifier = modifier) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(horizontalSpace.dp),
                modifier = modifier
            ) {
                for (columnIndex in 0 until columnCount) {
                    val itemIndex = rowIndex * columnCount + columnIndex
                    if (itemIndex < size) {
                        Box(
                            modifier = Modifier.weight(1F, fill = true),
                            propagateMinConstraints = true
                        ) {
                            itemContent(itemIndex,data[itemIndex])
                        }
                    } else {
                        Spacer(Modifier.weight(1F, fill = true))
                    }
                }
            }
            if(rowIndex<data.size){
                Spacer(modifier = Modifier.height(verticalSpace.dp))
            }
        }

    }
}
