package com.yeq.fhtfortune

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nrq.elestickers.ext.fromJson
import com.nrq.elestickers.ext.spGetStr
import com.nrq.elestickers.ext.spSetStr
import com.nrq.elestickers.ext.toJson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class APPVM : ViewModel() {
    private var _collectListState = MutableStateFlow(mutableListOf<String>())
    val collectListState: StateFlow<List<String>> = _collectListState


    fun listAdd0(str: String) {
        viewModelScope.launch {
            val newList = _collectListState.value.toMutableList()
            newList.add(0,str)
            _collectListState.value = newList
            updateSp(_collectListState.value.toJson())
        }
    }

    fun removeAt(index: Int) {
        viewModelScope.launch {
            val newList = _collectListState.value.toMutableList()
            newList.removeAt(index)
            _collectListState.value = newList
            updateSp(_collectListState.value.toJson())
        }
    }

    fun removeAll() {
        viewModelScope.launch {
            val newList = _collectListState.value.toMutableList()
            newList.clear()
            _collectListState.value = newList
            updateSp(_collectListState.value.toJson())
        }
    }

    fun updateSp(beanJson: String) {
        viewModelScope.launch {
            spSetStr(mContext.getString(R.string.app_name), beanJson)
        }
    }

    init {
        viewModelScope.launch {
            if (spGetStr(mContext.getString(R.string.app_name)).isEmpty()) {
                spSetStr(mContext.getString(R.string.app_name), mutableListOf<String>().toJson())
            } else {
                _collectListState.value =
                    spGetStr(mContext.getString(R.string.app_name)).fromJson<MutableList<String>>()
            }
        }
    }
}



