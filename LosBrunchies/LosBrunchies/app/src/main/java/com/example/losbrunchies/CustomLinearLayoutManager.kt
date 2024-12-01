package com.example.losbrunchies

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CustomLinearLayoutManager(context: Context?, orientation: Int, reverseLayout: Boolean) :
    LinearLayoutManager(context, orientation, reverseLayout) {

    override fun calculateExtraLayoutSpace(state: RecyclerView.State, extraLayoutSpace: IntArray) {
        super.calculateExtraLayoutSpace(state, extraLayoutSpace)
        // Reducir o eliminar el espacio adicional
        extraLayoutSpace[0] = 0 // Padding inicial
        extraLayoutSpace[1] = 0 // Padding final
    }
}