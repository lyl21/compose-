import androidx.compose.runtime.snapshots.SnapshotStateList


fun <T> SnapshotStateList<T>.updateElement(
    predicate: (T) -> Boolean, transform: (T) -> T
) {
    this.find(predicate)?.let {
        val tempData = it
        val index = this.indexOf(tempData)
        this.removeAt(index)
        this.add(index, transform(it))
    }
}


//use
newData.updateElement(predicate = { it == newData[index]}, transform = {
                                it.copy(isChecked = true)
                            })