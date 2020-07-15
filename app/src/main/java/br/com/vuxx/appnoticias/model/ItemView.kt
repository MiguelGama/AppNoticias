package br.com.vuxx.appnoticias.model
import java.io.Serializable
class ItemView: Serializable{
    var campo1: String? = null
    var campo2: String? = null
    var campo3: String? = null
    var urlImage: String? = null
    var descricao: String? = null

    constructor()
    constructor(campo1: String?, campo2: String?, campo3: String?, urlImage: String?, descricao: String?) {
        this.campo1 = campo1
        this.campo2 = campo2
        this.campo3 = campo3
        this.urlImage = urlImage
        this.descricao = descricao
    }
}
