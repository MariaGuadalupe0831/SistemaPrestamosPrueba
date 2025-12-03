package com.example.aplicacionprueba.Vista.user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionprueba.R // Asegúrate de que este R sea correcto
import com.example.aplicacionprueba.Modelo.Noticias
class NewsAdapter(private var newsList: List<Noticias>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {


    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // Asumiendo que has creado un layout XML llamado 'item_noticia.xml'
        // con estos IDs:
        val tvTitulo: TextView = itemView.findViewById(R.id.tvTituloNoticia)
        val tvDescripcion: TextView = itemView.findViewById(R.id.tvDescripcionNoticia)
        val tvFecha: TextView = itemView.findViewById(R.id.tvFechaPublicacion)

        fun bind(noticia: Noticias) {
            tvTitulo.text = noticia.titulo
            tvDescripcion.text = noticia.descripcion
            tvFecha.text = "Publicado: ${noticia.fechaPublicacion}"

            // Opcional: Configurar listener de clic si deseas ver el detalle completo.
        }

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_noticia, // Asegúrate de crear este layout XML
            parent,
            false
        )
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: NewsViewHolder,
        position: Int
    ) {
        holder.bind(newsList[position])
    }

    override fun getItemCount(): Int {
        return newsList.size
    }
    /**
     * Método público para actualizar la lista de noticias cuando se reciben datos de la API.
     * Este método es llamado desde UserDashboardActivity.displayNews().
     */
    fun updateData(newNewsList: List<Noticias>) {
        newsList = newNewsList
        notifyDataSetChanged() // Notifica al RecyclerView que los datos han cambiado.
    }

}