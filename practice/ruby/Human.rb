class Human
  @@species = 'H.sapiens'

  def initialize(name, age=0)
    @name = name
    @age = age
  end

  def name=(name)
    @name = name
  end

  def name
    @name
  end

  attr_reader :age

  def self.say(msg)
    puts msg
  end

  def species
    @@species
  end
end
