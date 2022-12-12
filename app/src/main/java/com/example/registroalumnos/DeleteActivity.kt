package com.example.registroalumnos

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.registroalumnos.basedatos.Alumnos
import com.example.registroalumnos.basedatos.ListaAlumnos.Companion.database
import com.example.registroalumnos.databinding.ActivityDeleteBinding
import com.example.registroalumnos.menu.MenuActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DeleteActivity : MenuActivity() {

    private lateinit var binding: ActivityDeleteBinding
    private lateinit var listaAlumnos: MutableList<Alumnos>

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)


        listaAlumnos = ArrayList()

        binding.BotonEliminar.setOnClickListener(){

            var nombreAlumno = binding.EliminarNombre.text.toString()



            if (nombreAlumno.isEmpty())
            {
                Toast.makeText(this, "No puede haber campos vacíos", Toast.LENGTH_SHORT).show()
            }
            else
            {
                var alumno = Alumnos(nombre = nombreAlumno)

                eliminarAlumno(alumno)
                Toast.makeText(this, "Alumno eliminado", Toast.LENGTH_SHORT).show()


                clean()

                closeKey()

            }
        }


    }
    fun eliminarAlumno(nombreAlumno: Alumnos){
        CoroutineScope(Dispatchers.IO).launch {
            database.ListaDAO().borrarAlumnos(nombreAlumno.nombre)
        }
    }

    // Funcion para cerrar el teclado
    fun closeKey() {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }

    // Limpiar
    fun clean() {
        binding.EliminarNombre.text.clear()
    }

}