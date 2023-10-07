package com.example.project6

class ItemData(val title: String, val description: String) {
    companion object {
        private var lastNoteID = 0
        fun createItemDataDefaultList(numItems: Int) : ArrayList<ItemData> {
            val items = ArrayList<ItemData>()
            for (i in 1..numItems) {
                items.add(ItemData("Note " + (++lastNoteID), "Blank description"))
            }
            return items
        }
    }
}