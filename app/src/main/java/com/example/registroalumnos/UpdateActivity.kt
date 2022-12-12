package com.example.registroalumnos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.registroalumnos.basedatos.Alumnos
import com.example.registroalumnos.basedatos.ListaAlumnos.Companion.database
import com.example.registroalumnos.databinding.ActivityUpdateBinding
import com.example.registroalumnos.menu.MenuActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateActivity : MenuActivity() {

    private lateinit var binding : ActivityUpdateBinding
    private lateinit var listaAlumnos: MutableList<Alumnos>


    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listaAlumnos = ArrayList()


        binding.Actualizar.setOnClickListener(){

            var nombreAlumno = binding.NombreActualizado.text.toString()
            var cursoAlumno = binding.TBCursoActualizar.text.toString()


            if (nombreAlumno.isEmpty() || cursoAlumno.isEmpty())
            {
                Toast.makeText(this, "No puede haber campos vacíos", Toast.LENGTH_SHORT).show()
            }
            else
            {
                var alumno = Alumnos(nombre = nombreAlumno, curso = cursoAlumno)

                actualizarAlumno(alumno)
                Toast.makeText(this, "Alumno actualizado", Toast.LENGTH_SHORT).show()
                clean()
                closeKey()

            }
        }

    }

    fun actualizarAlumno(nombreAlumno: Alumnos){
        CoroutineScope(Dispatchers.IO).launch {
            database.ListaDAO().actualizarAlumno(nombreAlumno.nombre, nombreAlumno.curso)
        }
    }

    // Funcion para cerrar el teclado
    fun closeKey() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as android.view.inputmethod.InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

   // Limpiar
    fun clean(){
        binding.NombreActualizado.text.clear()
        binding.TBCursoActualizar.text.clear()
    }
}