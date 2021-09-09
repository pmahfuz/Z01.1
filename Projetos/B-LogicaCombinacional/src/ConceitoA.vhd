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
entity ConceitoA is
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
architecture rtl of ConceitoA is
component binarioToBcd is
  generic(N: positive := 10);
    port(
        clk, reset: in std_logic;
        binary_in: in std_logic_vector(N-1 downto 0);
        bcd0, bcd1, bcd2, bcd3, bcd4: out std_logic_vector(3 downto 0)
    );
end component;

--------------
-- signals
--------------

signal bcd0_hex : std_logic_vector(3 downto 0);
signal bcd1_hex : std_logic_vector(3 downto 0);
signal bcd2_hex : std_logic_vector(3 downto 0);

begin

u1: binarioToBcd port map(
		clk => CLOCK_50,
		reset => '0',
      binary_in => SW,
      bcd0 => bcd0_hex,
		bcd1 => bcd1_hex,
		bcd2 => bcd2_hex);
		
---------------
-- implementacao
---------------

with bcd2_hex select
		HEX2 <= "0000001" when "0000", --numero 0 
				  "1001111" when "0001", --numero 1
				  "0010010" when "0010", --numero 2
				  "0000110" when "0011", --numero 3
				  "1001100" when "0100", --numero 4
				  "0100100" when "0101", --numero 5
				  "0100000" when "0110", --numero 6
				  "0001111" when "0111", --numero 7
				  "1111111" when "1000", --numero 8
				  "0000100" when others;

with bcd1_hex select
		HEX1 <= "0000001" when "0000", --numero 0 
				  "1001111" when "0001", --numero 1
				  "0010010" when "0010", --numero 2
				  "0000110" when "0011", --numero 3
				  "1001100" when "0100", --numero 4
				  "0100100" when "0101", --numero 5
				  "0100000" when "0110", --numero 6
				  "0001111" when "0111", --numero 7
				  "1111111" when "1000", --numero 8
				  "0000100" when others;


with bcd0_hex select
		HEX0 <= "0000001" when "0000", --numero 0 
				  "1001111" when "0001", --numero 1
				  "0010010" when "0010", --numero 2
				  "0000110" when "0011", --numero 3
				  "1001100" when "0100", --numero 4
				  "0100100" when "0101", --numero 5
				  "0100000" when "0110", --numero 6
				  "0001111" when "0111", --numero 7
				  "1111111" when "1000", --numero 8
				  "0000100" when others;

end rtl;