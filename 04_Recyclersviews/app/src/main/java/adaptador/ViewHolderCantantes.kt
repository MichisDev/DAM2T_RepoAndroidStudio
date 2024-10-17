package adaptador

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a04_recyclersviews.R

// Inicializador de las variables por  item
class ViewHolderCantantes (itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textView: TextView = itemView.findViewById(R.id.txCantante)

}