package com.eugene.androidonkotlin.view.details

import android.Manifest
import android.app.AlertDialog
import android.content.ContentResolver
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.eugene.androidonkotlin.R

class ContentProviderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_content_provider, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkpermission();
    }

    private fun checkpermission() {
        context?.let {
            when {
                ContextCompat.checkSelfPermission(it, android.Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED ->  { getContacts() }
                shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS) -> {
                    AlertDialog.Builder(it)
                        .setTitle("Доступ")
                        .setMessage("Надо")
                        .setPositiveButton("Да") { _, _ -> requestPermission() }
                        .setNegativeButton("Нет") {dialog, _ -> dialog.dismiss()}
                        .create()
                        .show()
                }
                else -> {
                    requestPermission()
                }
            }
        }
    }

    private fun requestPermission() {
        requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) {
            REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getContacts()
                } else {
                    context?.let {
                        AlertDialog.Builder(it)
                            .setTitle("Дотсуп")
                            .setMessage("Надо")
                            .setNegativeButton("закрыть") { dialog, _ -> dialog.dismiss() }
                            .create()
                            .show()
                    }
                }
                return
            }
        }
    }

    private fun getContacts() {
        context?.let {
            val contentResolver: ContentResolver = it.contentResolver
            val cursorWithContacts: Cursor? = contentResolver.query(
                ContactsContract.Contacts.CONTENT_URI,
                null,
                null,
                null,
                ContactsContract.Contacts.DISPLAY_NAME + " ASC"
            )
            cursorWithContacts?.let { cursor ->
                for(i in 0..cursor.count) {
                    if (cursor.moveToPosition(i)) {
                        val name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                        addView(it, name)
                    }
                }
            }
            cursorWithContacts?.close()
        }
    }

    private fun addView(context: Context, textToShow: String) {
        val container = view?.findViewById<TextView>(R.id.container_for_contacts)
        container?.text = textToShow
    }


    companion object {
        @JvmStatic
        fun newInstance() = ContentProviderFragment()
        const val REQUEST_CODE = 54
    }
}