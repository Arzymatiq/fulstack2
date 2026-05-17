package com.example.fulstack

import android.app.AlertDialog
import android.content.Context
import androidx.navigation.NavController

class DeleteHandler {

    fun showDeleteDialog(
        context: Context,
        productId: String,
        navController: NavController,
        onConfirm: (String) -> Unit
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Подтверждение удаления")
        builder.setMessage("Вы уверены, что хотите удалить товар с ID: $productId?")

        builder.setPositiveButton("Удалить") { dialog, _ ->
            onConfirm(productId)

            dialog.dismiss()

            navController.popBackStack()
        }

        builder.setNegativeButton("Отмена") { dialog, _ ->
            dialog.dismiss()
        }

        val alert = builder.create()
        alert.show()
    }
}