package adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.losbrunchies.R
import modelo.Huevo


class HuevoAdapter(private val huevoList: MutableList<Huevo>) :
    RecyclerView.Adapter<HuevoAdapter.HuevoViewHolder>() {

    private val selectedItems = mutableListOf<Int>()

    inner class HuevoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.iv_imagen_huevo)
        val textView: TextView = itemView.findViewById(R.id.tv_nombre_huevo)
    }


    // Este método se llama cuando se necesita crear un nuevo ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HuevoViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_huevo, parent, false)
        return HuevoViewHolder(itemView)
    }

    //Este método se llama para actualizar el contenido de un ViewHolder existente
    override fun onBindViewHolder(holder: HuevoViewHolder, position: Int) {

        val huevo = huevoList[position]
        holder.textView.text = huevo.nombreHuevo

        // Configurar la imagen y el nombre de la receta
        val imageResourceId = holder.itemView.context.resources.getIdentifier(
            huevo.imagenHuevo, "drawable", holder.itemView.context.packageName
        )

        holder.imageView.setImageResource(imageResourceId)


        // Definimos el evento click para cad elemento de la lista

        //definimos el evento long click para cada elemento de la lista

    }

    override fun getItemCount(): Int {
        return huevoList.size
    }
}