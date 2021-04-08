package com.example.mbulaktestapp

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.widget.Toast

fun View.show(){
    this.visibility = View.VISIBLE
}
fun View.hide(){
    this.visibility = View.GONE
}

fun Context.showToast(message:String, duration:Int = Toast.LENGTH_SHORT){
    Toast.makeText(this,message,duration).show()
}
fun Context.showAlert(message:String, onclickListener:(dialog: DialogInterface?, which: Int)-> Unit){
    val builder = AlertDialog.Builder(this)
    builder.setMessage(message)
    builder.setPositiveButton(android.R.string.yes) { dialog, which ->
        onclickListener(dialog,which)
    }
    builder.show()
}