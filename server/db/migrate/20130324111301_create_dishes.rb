class CreateDishes < ActiveRecord::Migration
  def change
    create_table :dishes do |t|
      t.integer :DishID
      t.string :DishName
      t.decimal :Price
      t.decimal :Discount
      t.string :Currency
      t.string :Tag
      t.string :DishImage
      t.text :Description

      t.timestamps
    end
  end
end
