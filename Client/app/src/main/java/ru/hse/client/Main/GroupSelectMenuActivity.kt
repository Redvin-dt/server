package ru.hse.client.Main

import android.content.Intent
import android.os.Bundle
import android.widget.SimpleAdapter
import androidx.core.content.ContextCompat
import ru.hse.client.BaseActivity
import ru.hse.client.R
import ru.hse.client.databinding.BaseBinding
import ru.hse.client.databinding.GroupSelectMenuBinding
import ru.hse.client.user

class GroupSelectMenuActivity : BaseActivity() {

    private lateinit var binding: GroupSelectMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = GroupSelectMenuBinding.inflate(layoutInflater)
        setContentView(R.layout.group_select_menu)

        createGroupList()
        setNewGroupButton()
    }

    private fun createGroupList(){
        var data : MutableList<Map<String, String>> = mutableListOf()

        for (group in user.getUserGroups()) {
            data.add(mapOf(
                    KEY_TITLE to group.name,
                    KEY_ADMIN to group.admin.login
            )
            )
        }

        val adapter = SimpleAdapter(
                this,
                data,
                android.R.layout.simple_list_item_2,
                arrayOf(KEY_TITLE, KEY_ADMIN),
                intArrayOf(android.R.id.text1, android.R.id.text2)
        )
        binding.groupSearchList.adapter = adapter
    }

    private fun setNewGroupButton() {
        val button = binding.createGroupButton

        button.setOnClickListener {
            val intent = Intent(this@GroupSelectMenuActivity, GroupCreateActivity::class.java)
            ContextCompat.startActivity(this@GroupSelectMenuActivity, intent, null)
        }
    }

    companion object{
        @JvmStatic val KEY_TITLE = "title"
        @JvmStatic val KEY_ADMIN = "admin"
    }
}