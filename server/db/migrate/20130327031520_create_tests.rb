class CreateTests < ActiveRecord::Migration
  def change
    create_table :tests do |t|
      t.string :username
      t.string :id

      t.timestamps
    end
  end
end
