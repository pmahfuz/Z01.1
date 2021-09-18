-- Elementos de Sistemas
-- by Luciano Soares
-- BinaryDigit.vhd

Library ieee;
use ieee.std_logic_1164.all;

entity BinaryDigit is
	port(
		clock:   in STD_LOGIC;
		input:   in STD_LOGIC;
		load:    in STD_LOGIC;
		output: out STD_LOGIC
	);
end entity;

architecture arch of BinaryDigit is

	component FlipFlopD is
		port(
			clock:  in std_logic;
			d:      in std_logic;
			clear:  in std_logic;
			preset: in std_logic;
			q:     out std_logic
		);
	end component;

	component Mux2Way is
		port (
			a:   in  STD_LOGIC;
			b:   in  STD_LOGIC;
			sel: in  STD_LOGIC;
			q:   out STD_LOGIC);
	end component;

	signal dffout,muxout: std_logic;

begin
	Mux0 : Mux2Way
	port map
	(
		b => input,
		a => dffout,
		-- essa parte do código me deixou meio confuso, mas o prof 
		-- explicou. A entrada b do mux é ativada quando a chave sel = 1
		-- no flip-flop, queremos ativar a memória (fazer o load)
		-- ativando o load. Se não ficar claro, fale comigo
		sel => load,
		q => muxout
	);
	
	FF0 : FlipFlopD
	port map
	(
		clock => clock,
		d => muxout,
		-- não queremos fixar o flip-flop nem
		-- em 0 nem em 1, então tem que ser '0'
		clear => '0',
		preset => '0',
		q => dffout
	);
	-- sim, dffout liga a saída do flip-flop
	-- tanto à entrada a do mux quanto ao output

	-- tem que ser fora pq port map só liga uma coisa
	-- a outra
	output <= dffout;
end architecture;
