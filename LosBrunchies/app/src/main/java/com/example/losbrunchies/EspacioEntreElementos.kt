package com.example.losbrunchies

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class EspacioEntreElementos(private val espacio: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.right = espacio // Espacio a la derecha de cada elemento
    }
}