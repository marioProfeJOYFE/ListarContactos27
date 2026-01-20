package com.mrh.listarcontactos

import java.math.BigInteger

data class Contacto(
    var nombre: String,
    var apellidos: String,
    var mail: String,
    var telefono: BigInteger
)
