class CreateOrders < ActiveRecord::Migration
  def change
    create_table :orders do |t|
      t.integer :id
      t.text :date_create
      t.integer :table
      t.string :dishes
      t.decimal :price
      t.string :currency

      t.timestamps
    end
  end
end
