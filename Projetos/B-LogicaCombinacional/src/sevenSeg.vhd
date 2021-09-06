library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity sevenSeg is
	port (
			bcd : in  STD_LOGIC_VECTOR(3 downto 0); 
			leds: out STD_LOGIC_VECTOR(6 downto 0));
end entity;

architecture arch of sevenSeg is
begin
	when "0000" => sevenSeg <= "0000001"  -- 0
	when "0001" => sevenSeg <= "1001111"  -- 1
	when "0010" => sevenSeg <= "0010010"  -- 2
	when "0011" => sevenSeg <= "0000111"  -- 3
	when "0100" => sevenSeg <= "1001100"  -- 4
	when "0101" => sevenSeg <= "0100100"  -- 5
	when "0110" => sevenSeg <= "0100000"  -- 6
	when "0111" => sevenSeg <= "0001111"  -- 7
	when "1000" => sevenSeg <= "0000000"  -- 8
	when "1001" => sevenSeg <= "0001100"  -- 9
end architecture;
