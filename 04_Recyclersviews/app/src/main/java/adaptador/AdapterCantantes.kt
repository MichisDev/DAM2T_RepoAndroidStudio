package adaptador

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a04_recyclersviews.R

// val listaCantantes = cantantes en el profe
class AdapterCantantes(private val listaCantantes: ArrayList<String>) :
    RecyclerView.Adapter<ViewHolderCantantes>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCantantes {
        // Inflamos el layout de cada elemento
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_cantante, parent, false)
        return ViewHolderCantantes(view)
    }

    override fun onBindViewHolder(holder: ViewHolderCantantes, position: Int) {

        // Inicializamos list de cantantes
        val cantante = listaCantantes[position]
        holder.textView.text = cantante

        // Asignamos el click listener, al hacer clic el fondo cambia de color
        holder.itemView.setOnClickListener {
            holder.itemView.setBackgroundResource(R.color.purple)
        }

        // Al dejar pulsado se elimina el item de la lista
        holder.itemView.setOnLongClickListener {
            listaCantantes.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, listaCantantes.size)
            true
        }
    }

    override fun getItemCount(): Int {
        return listaCantantes.size
    }
}
