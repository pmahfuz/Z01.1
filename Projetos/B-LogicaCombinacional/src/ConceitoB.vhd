----------------------------
-- Bibliotecas ieee       --
----------------------------
library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;
use work.all;

----------------------------
-- Entrada e saidas do bloco
----------------------------
entity ConceitoB is
	port(
		CLOCK_50 : in  std_logic;
		SW       : in  std_logic_vector(9 downto 0);
        HEX0     : out std_logic_vector(6 downto 0); -- 7seg0
        HEX1     : out std_logic_vector(6 downto 0); -- 7seg0
        HEX2     : out std_logic_vector(6 downto 0); -- 7seg0
		LEDR     : out std_logic_vector(9 downto 0)
	);
end entity;

----------------------------
-- Implementacao do bloco --
----------------------------
architecture rtl of ConceitoB is

--------------
-- signals
--------------

---------------
-- implementacao
---------------
begin

	with SW(3 downto 0) select
		HEX2 <= "1000000" when "0000", --numero 0 
				"1111001" when "0001", --numero 1
				"0100100" when "0010", --numero 2
				"0110000" when "0011", --numero 3
				"0011001" when "0100", --numero 4
				"0010010" when "0101", --numero 5
				"0000010" when "0110", --numero 6
				"1111000" when "0111", --numero 7
				"1111111" when "1000", --numero 8
				"0010000" when "1001", --numero 9
				"0000100" when "1010", --numero A
				"0000011" when "1011", --numero B
				"1000110" when "1100", --numero C
				"0100001" when "1101", --numero D
				"0000110" when "1110", --numero E
				"0001110" when others; --numero F

	
	with SW(7 downto 4) select
		HEX1 <= "1000000" when "0000", --numero 0 
				"1111001" when "0001", --numero 1
				"0100100" when "0010", --numero 2
				"0110000" when "0011", --numero 3
				"0011001" when "0100", --numero 4
				"0010010" when "0101", --numero 5
				"0000010" when "0110", --numero 6
				"1111000" when "0111", --numero 7
				"1111111" when "1000", --numero 8
				"0010000" when "1001", --numero 9
				"0000100" when "1010", --numero A
				"0000011" when "1011", --numero B
				"1000110" when "1100", --numero C
				"0100001" when "1101", --numero D
				"0000110" when "1110", --numero E
				"0001110" when others; --numero F


	with SW(9 downto 8) select
		HEX0 <= "1000000" when "00",   --numero 0 
				"1111001" when "01",   --numero 1
				"0100100" when "10",   --numero 2
				"0110000" when others; --numero 3
				
end rtl;

