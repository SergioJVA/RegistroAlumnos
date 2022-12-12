package com.example.registroalumnos.basedatos

import androidx.room.*

@Dao
interface DAO {


    @Insert
   suspend fun insertarAlumnos(alumnos: Alumnos)

    @Query ("SELECT * FROM alumnos")
    suspend fun todosAlumnos(): MutableList<Alumnos>

    @Query("DELETE FROM alumnos WHERE nombre like :nombre")
    suspend fun borrarAlumnos(nombre: String): Int

    @Query("SELECT * FROM alumnos WHERE nombre like :nombre")
    fun obteneralumnospornombre(nombre:String): Alumnos

    @Query
        ("UPDATE alumnos SET curso = :curso WHERE nombre like :nombre")
    suspend fun actualizarAlumno(nombre: String, curso: String): Int


}