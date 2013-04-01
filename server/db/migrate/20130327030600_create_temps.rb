class CreateTemps < ActiveRecord::Migration
  def change
    create_table :temps do |t|
      t.string :username
      t.integer :id

      t.timestamps
    end
  end
end
