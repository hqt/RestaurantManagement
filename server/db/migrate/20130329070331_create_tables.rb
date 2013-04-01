class CreateTables < ActiveRecord::Migration
  def change
    create_table :tables do |t|
      t.integer :id
      t.integer :no
      t.string :status

      t.timestamps
    end
  end
end
