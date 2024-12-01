package adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.losbrunchies.R
import modelo.Receta

class RecetaAdapter(private val recetas: MutableList<Receta>) :
    RecyclerView.Adapter<RecetaAdapter.RecetaViewHolder>() {

    private val selectedItems = mutableListOf<Int>()

    inner class RecetaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.iv_imagen_receta)
        val textView: TextView = itemView.findViewById(R.id.tv_nombre_receta)
    }


    // Este método se llama cuando se necesita crear un nuevo ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecetaViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_receta, parent, false)
        return RecetaViewHolder(itemView)
    }

    //Este método se llama para actualizar el contenido de un ViewHolder existente
    override fun onBindViewHolder(holder: RecetaViewHolder, position: Int) {

        val receta = recetas[position]
        holder.textView.text = receta.nombre

        // Configurar la imagen y el nombre de la receta
        val imageResourceId = holder.itemView.context.resources.getIdentifier(
            receta.nombreimagen, "drawable", holder.itemView.context.packageName
        )

        holder.imageView.setImageResource(imageResourceId)


        // Definimos el evento click para cad elemento de la lista

        //definimos el evento long click para cada elemento de la lista

    }

    override fun getItemCount(): Int {
        return recetas.size
    }
}