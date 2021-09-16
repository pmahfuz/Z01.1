-- Elementos de Sistemas
-- by Luciano Soares
-- comparador16.vhd

library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity comparador16 is
   port(
	     a    : in STD_LOGIC_VECTOR(15 downto 0);
       zr   : out STD_LOGIC;
       ng   : out STD_LOGIC
   );
end comparador16;

architecture rtl of comparador16 is
  -- Aqui declaramos sinais (fios auxiliares)
  -- e componentes (outros módulos) que serao
  -- utilizados nesse modulo.

begin
	with a select
	zr <= '1' when "0000000000000000",
			'0' when others;
			
	with a(15) select
		ng <= '1' when '1',
				'0' when others;

end architecture;
